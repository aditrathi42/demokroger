package com.example.demokroger.demokroger.service;
import com.example.demokroger.demokroger.error.UserNotFoundException;
import com.example.demokroger.demokroger.model.User;
import com.example.demokroger.demokroger.repository.UserRepository;
import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Value("${project-id}")
    String project_id;


    public User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee Not Found!"));
    }

    public String getBigqueryResult() throws Exception {
        BigQuery bq= BigQueryOptions.newBuilder().setProjectId(project_id).build().getService();
        final String GET_WORD_COUNT =
                "SELECT word, word_count FROM `bigquery-public-data.samples.shakespeare` WHERE corpus='juliuscaesar' ORDER BY word_count DESC limit 10;";
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(GET_WORD_COUNT).build();
        Job queryJob = bq.create(JobInfo.newBuilder(queryConfig).build());
        queryJob = queryJob.waitFor();

        if (queryJob == null) {
            throw new Exception("job no longer exists");
        }

        if (queryJob.getStatus().getError() != null) {
            throw new Exception(queryJob.getStatus().getError().toString());
        }

        System.out.println("word\tword_count");
        TableResult result = queryJob.getQueryResults();
        for (FieldValueList row : result.iterateAll()) {
            // We can use the `get` method along with the column
            // name to get the corresponding row entry
            String word = row.get("word").getStringValue();
            int wordCount = row.get("word_count").getNumericValue().intValue();
            System.out.printf("%s\t%d\n", word, wordCount);
        }
        return "";
    }
}
