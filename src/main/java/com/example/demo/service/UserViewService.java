package com.example.demo.service;

import com.example.demo.dto.response.UserViewResponse;
import java.util.List;

public interface UserViewService {
    List<UserViewResponse> getAllUserViews();
}
