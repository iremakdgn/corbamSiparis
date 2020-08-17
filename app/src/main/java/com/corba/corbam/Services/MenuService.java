package com.corba.corbam.Services;

import com.corba.corbam.Entities.Masa;
import com.corba.corbam.Entities.Menu;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MenuService {
    static RestInterface restInterface;
    public List<Menu> GetMenu() {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call<List<Menu>> call = restInterface.getMenu();
        try {
            List<Menu> menuler = call.execute().body();
            return menuler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Menu GetMenuByUrunId(int urunid) {
        restInterface = APIClient.getClient().create(RestInterface.class);
        Call <Menu> call = restInterface.getMenuByUrunId(urunid);
        try {
            Menu menuler = call.execute().body();
            return menuler;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
