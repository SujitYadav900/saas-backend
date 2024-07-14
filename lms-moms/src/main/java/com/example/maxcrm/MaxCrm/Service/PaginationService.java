package com.example.maxcrm.MaxCrm.Service;

public interface PaginationService {
    int findCount(String query);
    int findCount(int count);

}
