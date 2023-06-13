package com.tcs.slackintegration.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.google.gson.Gson;

	@Service
	public class AppService {

	    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);
	    private static final String NEW_LINE = "\n";

	    @Value("${slack.webhook}")
	    private String urlSlackWebHook;
	    
	    public String fetchUsername(String user_id) throws SlackApiException, IOException, InterruptedException {

	        String plainCredentials = "xoxb-524547269687-3400014607318-Fps4rL6BJvSTUcwInnC06CFl";

	        String authorizationHeader = "Bearer " + plainCredentials;

	        HttpClient client = HttpClient.newHttpClient();
	        String uri = String.format("https://slack.com/api/users.info?include_locale=true&user=%s",user_id); 
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(uri))
	                .GET()
	                .header("Authorization", authorizationHeader)
	                .build();
	        
	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        Gson gson = new Gson();
	        UserInfo userinfo = gson.fromJson(response.body(), UserInfo.class);
	        
	 
	        return userinfo.getUser().getReal_name();
		}
	    
	    public void replyMessage(String id, String ts, String text) throws SlackApiException, IOException, InterruptedException {

	        try {
	            URL url = new URL("https://slack.com/api/chat.postMessage");
	            String postData = "channel=" + id + "&text="+ text + "&thread_ts=" + ts;
	 
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.setRequestProperty("Authorization", "Bearer xoxb-524547269687-3400014607318-Fps4rL6BJvSTUcwInnC06CFl");
	            conn.setUseCaches(false);
	 
	            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
	                dos.writeBytes(postData);
	            }
	 
	            try (BufferedReader br = new BufferedReader(new InputStreamReader(
	                                                conn.getInputStream())))
	            {
	                String line;
	                while ((line = br.readLine()) != null) {
	                    System.out.println(line);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    	    
	    public Message fetchHistory(String id) throws SlackApiException, IOException, InterruptedException {

        String plainCredentials = "xoxb-524547269687-3400014607318-Fps4rL6BJvSTUcwInnC06CFl";

        String authorizationHeader = "Bearer " + plainCredentials;

        HttpClient client = HttpClient.newHttpClient();
        String uri = String.format("https://slack.com/api/conversations.history?channel=%s",id); 
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("Authorization", authorizationHeader)
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        History hist = gson.fromJson(response.body(), History.class);
        Message[] msg = hist.getMessages();
        
        Arrays.sort(msg, new Comparator<Message>() {
			@Override
			public int compare(Message o1, Message o2) {
				return o1.getTs().compareTo(o2.getTs());
			}
        });
        
        hist.setMessages(msg);
 
        return hist.getMessages()[hist.getMessages().length-1];
	}
	    
	    public void sendMessageToSlack(String message) {
	        StringBuilder messageBuilder = new StringBuilder();
	        messageBuilder.append(message + NEW_LINE);
	        process(messageBuilder.toString());
	    }
	    
	    public void sendMessageToSlack(String message, String res_url) {
	        StringBuilder messageBuilder = new StringBuilder();
	        messageBuilder.append(message + NEW_LINE);
	        process(messageBuilder.toString(), res_url);
	    }
	    
	    private void process(String message) {
	        Payload payload = Payload.builder()
	                .text(message)
	                .build();
	        try {
	            WebhookResponse webhookResponse = Slack.getInstance().send(
	                    urlSlackWebHook, payload);
	            LOGGER.info("code -> " + webhookResponse.getCode());
	            LOGGER.info("body -> " + webhookResponse.getBody());
	        } catch (IOException e) {
	            LOGGER.error("Unexpected Error! WebHook:" + urlSlackWebHook);
	        }
	    }

	    private void process(String message, String res_url) {
	        Payload payload = Payload.builder()
	                .text(message)
	                .build();
	        try {
	            WebhookResponse webhookResponse = Slack.getInstance().send(
	                    res_url, payload);
	            LOGGER.info("code -> " + webhookResponse.getCode());
	            LOGGER.info("body -> " + webhookResponse.getBody());
	        } catch (IOException e) {
	            LOGGER.error("Unexpected Error! WebHook:" + res_url);
	        }
	    }

	}
