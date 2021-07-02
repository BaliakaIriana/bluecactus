package com.snovibox.bluecactus.dto;

import com.snovibox.bluecactus.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulSigninResults {
    private String accessToken;
    private User user;
}
