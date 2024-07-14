package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
import java.util.List;
import java.util.Map;

public class Buttons {

    private List<Actions> actions;
    private List<QuickReplies> quickReplies;

    public List<Actions> getActions() {
        return actions;
    }

    public void setActions(List<Actions> actions) {
        this.actions = actions;
    }

    public List<QuickReplies> getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(List<QuickReplies> quickReplies) {
        this.quickReplies = quickReplies;
    }

    @Override
    public String toString() {
        return "Buttons{" +
                "actions=" + actions +
                ", quickReplies=" + quickReplies +
                '}';
    }
}
