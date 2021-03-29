package com.lpf.socket.live;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * <p>
 *
 * </P>
 *
 * @author 18030213
 * @since 2021/3/26
 **/
public class LiveChannelCache {
    private Channel channel;
    private ScheduledFuture scheduledFuture;

    public LiveChannelCache(Channel channel, ScheduledFuture scheduledFuture) {
        this.channel = channel;
        this.scheduledFuture = scheduledFuture;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
}
