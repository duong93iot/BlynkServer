package cc.blynk.server.core.model.widgets.others.webhook;

import cc.blynk.server.core.model.enums.PinMode;
import cc.blynk.server.core.model.enums.PinType;
import cc.blynk.server.core.model.widgets.OnePinWidget;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 05.09.16.
 */
public class WebHook extends OnePinWidget {

    public String url;

    //GET is always default so we don't do null checks
    public SupportedWebhookMethod method = SupportedWebhookMethod.GET;

    public Header[] headers;

    public String body;

    public transient volatile int failureCounter = 0;

    private boolean isValidUrl() {
        return url != null && !url.isEmpty() && url.regionMatches(true, 0, "http", 0, 4);
    }

    public boolean isValid(int webhookFailureLimit) {
        return isValidUrl() && failureCounter < webhookFailureLimit;
    }

    //a bit ugly but as quick fix ok
    public boolean isSameWebHook(int deviceId, byte pin, PinType type) {
        return super.isSame(deviceId, pin, type);
    }

    @Override
    public void sendAppSync(Channel appChannel, int dashId, int targetId) {
    }

    @Override
    public void sendHardSync(ChannelHandlerContext ctx, int msgId, int deviceId) {
    }

    @Override
    //supports only virtual pins
    public PinMode getModeType() {
        return null;
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
