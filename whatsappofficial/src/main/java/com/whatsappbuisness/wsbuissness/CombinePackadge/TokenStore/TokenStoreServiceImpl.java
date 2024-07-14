package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class TokenStoreServiceImpl implements TokenStoreService {
    @Autowired
    DataSource dataSource;
    @Autowired
    TokenStoreRepo tokenStoreRepo;


    @Override
    public TokenStoreDao validate(String id) {

        return tokenStoreRepo.findById(id).get();
    }

    private void delete(long id) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("delete from Tbl_Token where userId=? ;");
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception ew) {
            ew.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TokenStoreDao generate(UsermasterDao usermasterDao) {
        delete(usermasterDao.getId());
        TokenStoreDao tokenStoreDao = new TokenStoreDao();
        tokenStoreDao.setToken(UUID.randomUUID().toString());

        tokenStoreDao.setAccountId(usermasterDao.getAccountId());
        tokenStoreDao.setUserId(usermasterDao.getId());
        tokenStoreDao.setLastCreateBy(usermasterDao.getFullname());
        tokenStoreDao.setCreateAt(DateTiming.getDateRedable());
        return tokenStoreRepo.save(tokenStoreDao);
    }

    @Override
    public TokenStoreDao getToken(UsermasterDao usermasterDao) {
        TokenStoreDao tokenStoreDao = tokenStoreRepo.findAllByUserId(usermasterDao.getId());
        if (tokenStoreDao == null) {
            tokenStoreDao = new TokenStoreDao();
            tokenStoreDao.setToken(UUID.randomUUID().toString());
            tokenStoreDao.setAccountId(usermasterDao.getAccountId());
            tokenStoreDao.setUserId(usermasterDao.getId());
            tokenStoreDao.setLastCreateBy(usermasterDao.getFullname());
            tokenStoreDao.setCreateAt(DateTiming.getDateRedable());
            tokenStoreDao = tokenStoreRepo.save(tokenStoreDao);
        }
        return tokenStoreDao;
    }
}
