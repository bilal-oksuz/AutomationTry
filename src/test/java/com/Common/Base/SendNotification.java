package com.Common.Base;

import com.Common.Clients.Slack;
import org.json.JSONObject;

public class SendNotification
{
    public Slack slack=new Slack();
    Config config;

    public SendNotification()
    {

    }

    public SendNotification(Config config)
    {
        setConfig(config);
    }


    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }


    public Config getConfigByConfigAPI() {
        return Config.getConfig();
    }


}
