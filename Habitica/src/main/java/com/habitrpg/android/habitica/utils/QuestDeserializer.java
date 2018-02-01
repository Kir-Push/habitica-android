package com.habitrpg.android.habitica.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.habitrpg.android.habitica.models.inventory.Quest;
import com.habitrpg.android.habitica.models.inventory.QuestMember;
import com.habitrpg.android.habitica.models.inventory.QuestProgress;
import com.habitrpg.android.habitica.models.inventory.QuestProgressCollect;

import java.lang.reflect.Type;
import java.util.Map;

import io.realm.RealmList;

public class QuestDeserializer implements JsonDeserializer<Quest> {
    @Override
    public Quest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Quest quest = new Quest();

        if (obj.has("key") && !obj.get("key").isJsonNull()) {
            quest.setKey(obj.get("key").getAsString());
        } else {
            return quest;
        }
        if (obj.has("active")) {
            quest.setActive(obj.get("active").getAsBoolean());
        }
        if (obj.has("leader")) {
            quest.setLeader(obj.get("leader").getAsString());
        }
        if (obj.has("RSVPNeeded")) {
            quest.setRSVPNeeded(obj.get("RSVPNeeded").getAsBoolean());
        }
        if (obj.has("progress")) {
            QuestProgress progress = new QuestProgress();
            progress.setKey(quest.getKey());
            JsonObject progressObj = obj.get("progress").getAsJsonObject();
            if (progressObj.has("hp")) {
                progress.setHp(progressObj.get("hp").getAsInt());
            }
            if (progressObj.has("rage")) {
                progress.setRage(progressObj.get("rage").getAsInt());
            }
            if (progressObj.has("up")) {
                progress.setUp(progressObj.get("up").getAsInt());
            }
            if (progressObj.has("down")) {
                progress.setDown(progressObj.get("down").getAsInt());
            }
            if (progressObj.has("collect")) {
                progress.setCollect(new RealmList<>());
                for (Map.Entry<String, JsonElement> entry : progressObj.get("collect").getAsJsonObject().entrySet()) {
                    QuestProgressCollect collect = new QuestProgressCollect();
                    collect.key = entry.getKey();
                    collect.count = entry.getValue().getAsInt();
                    progress.getCollect().add(collect);
                }
            }
            quest.setProgress(progress);
        }

        if (obj.has("members")) {
            RealmList<QuestMember> members = new RealmList<>();
            for (Map.Entry<String, JsonElement> entry : obj.get("members").getAsJsonObject().entrySet()) {
                QuestMember member = new QuestMember();
                member.key = entry.getKey();
                if (entry.getValue().isJsonNull()) {
                    member.isParticipating = null;
                } else {
                    member.isParticipating = entry.getValue().getAsBoolean();
                }
                members.add(member);
            }
            quest.setMembers(members);
        }
        return quest;
    }
}
