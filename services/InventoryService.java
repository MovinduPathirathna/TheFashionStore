package services;
import entities.Item;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final ArrayList<Item> items = new ArrayList<>();
    private final String file = "data/inventory.txt";
    public InventoryService() {
        loadInventory();
    }

    public void saveInventory(){
        List<String> itemList = new ArrayList<>();
        for(Item item : items){
             itemList.add(item.toFile());
        }
        try {
            Files.write(Paths.get(file), itemList);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadInventory(){
        try{
            List<String> data = Files.readAllLines(Paths.get(file));
            for (int i=0; i<data.size(); i++){
                String[] itemData = data.get(i).split(",");
                items.add(new Item(itemData[0],itemData[1],itemData[2],itemData[3],itemData[4],Double.parseDouble(itemData[5]),Integer.parseInt(itemData[6])));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public Item getItemByCode(String code) {
        for (Item item : items) {
            if (item.getItemCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }

    public boolean addItem(Item item) {
        if (getItemByCode(item.getItemCode()) != null) {
            return false;
        }
        items.add(item);
        return true;
    }

    public boolean updateStock(String code, int quantity) {
        Item item = getItemByCode(code);
        if (item != null && item.getQuantity() + quantity >= 0) {
            item.setQuantity(item.getQuantity() + quantity);
            return true;
        }
        return false;
    }

    public ArrayList<Item> getLowStockItems(int threshold) {
        ArrayList<Item> lowStock = new ArrayList<>();
        for (Item item : items) {
            if (item.getQuantity() <= threshold) {
                lowStock.add(item);
            }
        }
        return lowStock;
    }
}
