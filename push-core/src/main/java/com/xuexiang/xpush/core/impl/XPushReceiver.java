/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.xpush.core.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.xuexiang.xpush.core.IPushReceiver;
import com.xuexiang.xpush.entity.XPushCommand;
import com.xuexiang.xpush.entity.XPushMsg;
import com.xuexiang.xpush.logs.PushLog;

import static com.xuexiang.xpush.core.XPushAction.RECEIVE_COMMAND_RESULT;
import static com.xuexiang.xpush.core.XPushAction.RECEIVE_MESSAGE;
import static com.xuexiang.xpush.core.XPushAction.RECEIVE_NOTIFICATION;
import static com.xuexiang.xpush.core.XPushAction.RECEIVE_NOTIFICATION_CLICK;


/**
 * 消息推送接收器
 *
 * @author xuexiang
 * @since 2019-08-15 18:04
 */
public abstract class XPushReceiver extends BroadcastReceiver implements IPushReceiver {

    @Override
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Parcelable parcelable = TransmitDataManager.parsePushData(intent);
        if (RECEIVE_COMMAND_RESULT.equals(action)) {
            onCommandResult(context, (XPushCommand) parcelable);
        } else if (RECEIVE_NOTIFICATION.equals(action)) {
            onNotification(context, (XPushMsg) parcelable);
        } else if (RECEIVE_NOTIFICATION_CLICK.equals(action)) {
            onNotificationClick(context,  (XPushMsg) parcelable);
        } else if (RECEIVE_MESSAGE.equals(action)) {
            onMessageReceived(context, (XPushMsg) parcelable);
        }
        PushLog.i(String.format("%s--%s", action, String.valueOf(parcelable)));
    }

    @Override
    public void onNotification(Context context, XPushMsg msg) {
        
    }
}
