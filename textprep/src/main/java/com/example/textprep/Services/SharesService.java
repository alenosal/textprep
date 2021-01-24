package com.example.textprep.Services;

import com.example.textprep.Repositories.SharesRepositories;
import com.example.textprep.entity.Shares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharesService {

    @Autowired
    private SharesRepositories sharesRepositories;

    public Shares createShare(String schemaId, String groupId, String userId) {
        Shares shares = new Shares(schemaId, groupId, userId);
        sharesRepositories.save(shares);

        return shares;
    }
}
