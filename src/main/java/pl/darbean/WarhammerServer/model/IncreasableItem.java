package pl.darbean.WarhammerServer.model;

public interface IncreasableItem {

    String ITEM_ID="ITEM_ID";
    String PLACE="PLACE";

    String getItemLabel();
    void setAdvance(int advance);
    int getAdvance();
    int getTotal();

}
