package com.wft.sky_gym;

import com.wft.sky_gym.Admin.UsersHelperClass;

import java.util.List;

public interface DataStatus {
     void DataIsLoaded(List<UsersHelperClass> users, List<String> Keys);
     void DataIsInserted();
     void DataIsUpdated();
     void DataIsDeleted();
}
