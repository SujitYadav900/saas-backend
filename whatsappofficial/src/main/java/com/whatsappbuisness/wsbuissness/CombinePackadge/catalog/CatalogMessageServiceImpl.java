package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogMessageServiceImpl implements CatalogMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogMessageServiceImpl.class);
    private static final String BASE_DOCUMENT_NAME = "CatalogMessageDao";

    private final SessionRetrievalService sessionRetrievalService;
    private final CatalogMessageRepo catalogMessageRepo;

    @Autowired
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CatalogMessageServiceImpl(SessionRetrievalService sessionRetrievalService, CatalogMessageRepo catalogMessageRepo, MongoTemplate mongoTemplate) {
        this.sessionRetrievalService = sessionRetrievalService;
        this.catalogMessageRepo = catalogMessageRepo;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseEntity<?> save(CatalogMessageDao catalogMessageDao, HttpServletRequest httpServletRequest) {
        TokenStoreDao tokenStoreDao = null;
        try {
            tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
        } catch (TokenGenerationErrorHandling ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Access Token");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        try {
            catalogMessageDao.setAccountId(tokenStoreDao.getAccountId());
            saveInDb(catalogMessageDao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Inserted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> saveFromPanel(CatalogMessageDao catalogMessageDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        catalogMessageDao.setAccountId(usermasterDao.getAccountId());
        String message = "Successfully Inserted";
        HttpStatus code = HttpStatus.CREATED;
        try {
            saveInDb(catalogMessageDao);
        } catch (Exception ex) {
            message = ex.getMessage();
            code = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(code).body(message);
    }

    @Override
    public ResponseEntity<?> update(CatalogMessageDao catalogMessageDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        catalogMessageDao.setUpdateDate(DateTiming.getDateRedable());
        catalogMessageDao.setAccountId(usermasterDao.getAccountId());
        String message = "Successfully Update";
        HttpStatus code = HttpStatus.CREATED;
        try {
            saveInDb(catalogMessageDao);
        } catch (Exception ex) {
            message = ex.getMessage();
            code = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(code).body(message);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        LOGGER.info("deleteById {}", id);
        try {
            catalogMessageRepo.deleteById(id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully Deleted");
    }


    private void saveInDb(CatalogMessageDao catalogMessageDao) throws Exception {
        try {
            catalogMessageDao.setDate(DateTiming.getDateRedable());
            catalogMessageDao.setDateFilter(DateTiming.getDateFilterDate());
            catalogMessageRepo.save(catalogMessageDao);
        } catch (Exception ex) {
            throw new Exception("Error saving CatalogMessageDao to the database", ex);
        }
    }

    @Override
    public void sendCatalogueMessage(CatalogMessageDao catalogMessageDao, SubscriptionDao subscriptionDao) throws JsonProcessingException {
        LOGGER.info("CatalogMessageServiceImpl sendCatalogueMessage {}", catalogMessageDao);

        String updatedJsonString = removeFieldsFromJson(catalogMessageDao);
        LOGGER.info("updatedJsonString {} ",updatedJsonString);



        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, updatedJsonString);
        Request request = new Request.Builder().url("https://graph.facebook.com/v16.0/" + subscriptionDao.getCloudAPIPhoneNumberID() + "/messages").method("POST", body).addHeader("Authorization", "Bearer " + subscriptionDao.getCloudAPIToken()).addHeader("Content-Type", "application/json").build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            LOGGER.info("SendCatalogueMessage After Hitting API response: {}", response);
            LOGGER.info("SendCatalogueMessage After Hitting API response: {}", response.body().string());
        } catch (IOException e) {
            LOGGER.error("ERROR OCCURRED:  {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private String removeFieldsFromJson(CatalogMessageDao catalogMessageDao) throws JsonProcessingException {
        Gson gson = new Gson();
        String json = gson.toJson(catalogMessageDao);

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(json);

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            // Remove "id" field
            jsonObject.remove("id");
            removeFields(jsonObject.getAsJsonObject("interactive"));
            return gson.toJson(jsonObject);
        }

        return json;
    }

    private void removeFields(JsonObject interactiveNode) {
        if (interactiveNode != null) {
            JsonElement actionNode = interactiveNode.get("action");
            if (actionNode != null && actionNode.isJsonObject()) {
                JsonArray sectionsArray = actionNode.getAsJsonObject().getAsJsonArray("sections");

                if (sectionsArray != null) {
                    for (JsonElement sectionNode : sectionsArray) {
                        JsonArray productItemsArray = sectionNode.getAsJsonObject().getAsJsonArray("product_items");

                        if (productItemsArray != null) {
                            for (JsonElement productNode : productItemsArray) {
                                productNode.getAsJsonObject().remove("variantsTitle");
                                productNode.getAsJsonObject().remove("quantity");
                                productNode.getAsJsonObject().remove("item_price");
                                productNode.getAsJsonObject().remove("currency");
                            }
                        }
                    }
                }
            }
        }
    }



    @Override
    public PaginationDao<?> get(Authentication authentication, int offset, int limit) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        PaginationDao<CatalogMessageDao> paginationDao = new PaginationDao<>();
        List<CatalogMessageDao> list = catalogMessageRepo.findAllByAccountIdOrderByAccountId(usermasterDao.getAccountId());
        paginationDao.setTotal(list.size());
        paginationDao.setData(list);
        return paginationDao;
    }

    @Override
    public CatalogMessageDao getById(String id) throws Exception {
        CatalogMessageDao catalogMessageDao = null;
        try {
            catalogMessageDao = catalogMessageRepo.findById(id).get();
        } catch (Exception ex) {
            throw new Exception("No value present");
        }
        return catalogMessageDao;
    }

    @Override
    public String getVariantsTitleByCatalogIdAndRetailerProductId(String catalogId, String productRetailerId) {
        LOGGER.info("getVariantsTitleByCatalogIdAndRetailerProductId catalogId>>>> {} productRetailerId>>>>> {}",catalogId,productRetailerId);

        try {
            Query query = new Query()
                    .addCriteria(Criteria.where("interactive.action.catalog_id").is(catalogId))
                    .addCriteria(Criteria.where("interactive.action.sections.product_items.product_retailer_id").is(productRetailerId));
            List<CatalogMessageDao> catalogMessage = mongoTemplate.find(query, CatalogMessageDao.class, BASE_DOCUMENT_NAME);
            LOGGER.info("GET DATA FROM DB >>>>>> {}",catalogMessage);
            if (catalogMessage != null) {
                for (Sections section : catalogMessage.get(0).getInteractive().getAction().getSections()) {
                    List<ProductItems> productItems = section.getProduct_items();
                    for (ProductItems productItem : productItems) {
                        if (productItem.getProduct_retailer_id().equalsIgnoreCase(productRetailerId)) {
                            return productItem.getVariantsTitle();
                        }
                    }
                }
            }
        }catch (Exception ex){
            LOGGER.info("ERROR OCCURRED {}",ex.getMessage());
            ex.printStackTrace();
        }


        return null;
    }


}
