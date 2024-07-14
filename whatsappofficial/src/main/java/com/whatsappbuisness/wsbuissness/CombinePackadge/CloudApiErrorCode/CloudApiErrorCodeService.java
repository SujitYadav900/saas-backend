package com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode;

public interface CloudApiErrorCodeService {

    CloudApiErrorCodeDao getCloudApiErrorStatus(String errorCode);
    String refreshCache();

}
