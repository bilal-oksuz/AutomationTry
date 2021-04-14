package com.Common.Clients;

import com.Common.Base.Config;
import org.json.JSONObject;
import java.io.File;

public class Slack
{
    private JSONObject body;
    Config config= Config.getConfig();
    RestTrigger restTrigger=new RestTrigger();

    public Slack()
    {

    }

    public Slack (String message)
    {
        setBody(new JSONObject().put("text",message));
    }

    public Slack (JSONObject message) {
        setBody(message);
    }


    public JSONObject createBodyforSlack(String body)
    {
        return new JSONObject().put("text",body);
    }

    public String tagHere()
    {
        return  "<!here>";
    }

    public String tagChannel()
    {
        return  "<!channel>";
    }

    public String tagPerson(String user)
    {
        return  "<@"+user+">";
    }


    public void setBody(JSONObject body)
    {
        this.body = body;
    }

    public JSONObject getBody()
    {
        return body;
    }

    public JSONObject createBodyWithLayout(String message)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text",message)));
        return body;
    }

    public JSONObject createBodyWithLayoutForUrlAndMessage(String message,String url)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text",message)));
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text","<"+url+"|UrlText>")));
        return body;
    }


    public JSONObject createBodyWithLayoutForImage(String imageUrl)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","image").put("image_url",imageUrl).put("alt_text","Image ToolTip")));
        return body;
    }

    public JSONObject createBodyWithLayoutForImageAndMessage(String imageUrl,String message)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text",message)));
        body.append("blocks",new JSONObject().put("type","section").putOpt("accessory",new JSONObject().put("type","image").put("image_url",imageUrl).put("alt_text","Image ToolTip")));
        return body;
    }

    public JSONObject createBodyForCodeBlocks(String message)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text","```"+message+"```")));
        return body;
    }

    public JSONObject createBodyForCodeBlocks(String message,String title)
    {
        JSONObject body=new JSONObject();
        body.put("text","text");
        body.append("blocks",new JSONObject().put("type","section").append("text","").putOpt("text",new JSONObject().put("type","mrkdwn").put("text",title+" ```"+message+"```")));
        return body;
    }

    public void fileUpload(File file,String channel)
    {
        RestTrigger restTrigger=new RestTrigger();
        restTrigger.SendRequestDataWithAuthorization(config.getSlackApiURL()+config.getFileUploadURL(),config.getSlackToken(),file,channel);
    }
    
    public static void main(String[] args)
    {
        Slack slack=new Slack();
    }


}
