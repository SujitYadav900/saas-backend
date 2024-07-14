package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CountryWisePriceServiceImpl implements CountryWisePriceService {


	private static final Logger logger = LoggerFactory.getLogger(CountryWisePriceService.class);
	@Autowired
	DataSource dataSource;
	@Autowired
	CountryWiseRepo countryWiseRepo;

	String dbName = "Accounts";
	String collectionName = "AccountMasterDocument";


	@Override
	public void initialize(long accountId) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO `Tbl_Country_Rates` (`accountId`, `countryCode`, `active`, `amt`) select " + accountId + ",Tbl_Country_Rates.countryCode,TRUE,Tbl_Country_Rates.amt from Tbl_Country_Rates where accountId=0");
			stmt.executeUpdate();
			logger.info("Intitalization of account {} completed ", accountId);

		} catch (Exception ew) {
			logger.error("Error Occured while executing  {}", ew);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Bean
	public void refreshCacheBean() {


		refreshCache();
	}

	@Override
	public CountryWiseRateRetDao get(CountryWisePricePk countryWisePricePk) {
		CountryWiseRateRetDao countryWiseRateRetDao;
		CountryWisePriceDao countryWisePriceDao=countryWiseRepo.findById(countryWisePricePk).orElse(null);
		if (countryWisePriceDao!=null) {

			if (countryWisePriceDao.isActive()) {
				countryWiseRateRetDao= new CountryWiseRateRetDao(countryWisePriceDao, StatusDao.OK);

			} else {
				countryWiseRateRetDao=  new CountryWiseRateRetDao(countryWisePriceDao, StatusDao.NOTALLOWED);
			}
		} else {
			countryWiseRateRetDao=   new CountryWiseRateRetDao(countryWisePriceDao, StatusDao.NOTFOUND);
		}
		return countryWiseRateRetDao;

	}

	@Override
	public CountryWisePriceDao update(CountryWisePriceDao countryWisePriceDao) {
		MongoResponse response = getParentIdFromMongo(countryWisePriceDao.getAccountId());
		if("INHERITED".equalsIgnoreCase(response.getCreditType())) {
			List<CountryWisePriceDao> parentPriceDao = countryWiseRepo.findByAccountId(response.getParentId());
			for(CountryWisePriceDao priceDao :parentPriceDao) {
				logger.info("Comapre the country rate is grater than of parent rate :" );
				if(priceDao.getCountryCode().equalsIgnoreCase(countryWisePriceDao.getCountryCode()) && countryWisePriceDao.getAuthenicationRates() >= priceDao.getAuthenicationRates()
						&& countryWisePriceDao.getMarketingRates() >= priceDao.getMarketingRates() && countryWisePriceDao.getUserInitiatedRates() >= priceDao.getUserInitiatedRates()
						&& countryWisePriceDao.getUtilityRates() >= priceDao.getUtilityRates()) {
					logger.info("Save the Rate into table" );
					return countryWiseRepo.save(countryWisePriceDao);
				}
			}

		}else if("SELF".equalsIgnoreCase(response.getCreditType())) {
			return countryWiseRepo.save(countryWisePriceDao);
		}else {
			logger.info("Child Rate is Less Than of Parent Rate:" );
		}
		return countryWisePriceDao;
	}

	@Override
	public void refreshCache() {

	}

	@Override
	public List<CountryWisePriceDao> getByAccountId(long accountId) {
		return countryWiseRepo.findAllByAccountIdOrderByCountryCodeAsc(accountId);
	}

	@Override
	public void delete(CountryWisePriceDao countryWisePriceDao) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement("delete from WSBUISSNES.Tbl_Country_Rates where accountId = ? and countryCode = ?");
			ps.setLong(1,countryWisePriceDao.getAccountId());
			ps.setString(2,countryWisePriceDao.getCountryCode());
			logger.info("The stmt is {}", ps);
			ps.executeUpdate();
			logger.info("The entry Deleted");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private MongoResponse getParentIdFromMongo(long accountId) {
		logger.info("Ready to fetch documents behalf of Client id:" );
		MongoResponse response = new MongoResponse();
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase(dbName);
			MongoCollection<Document> collection = database.getCollection(collectionName);
			logger.info("Make the connection with Mongo:" );
			Document query = new Document("_id", accountId); 
			try (MongoCursor<Document> cursor = collection.find(query).iterator()) {
				while (cursor.hasNext()) {
					logger.info("fetch  data one by one" );
					Document doc = cursor.next();
					logger.info("This is fetch documents according to Client id:" +doc.toJson());
					response.setParentId(doc.getLong("parentId"));
					response.setCreditType(doc.getString("creditType"));
					logger.info("fetch  data one by one :" +doc.getLong("parentId") );
					logger.info("fetch  data one by one:" +doc.getString("creditType") );
					return response;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}


}
