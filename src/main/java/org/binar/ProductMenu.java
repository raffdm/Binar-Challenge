package org.binar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductMenu {
    private Integer nomorMenu;
    private String namaMenu;
    private Integer hargaMenu;

    public ProductMenu(Integer nomorMenu, String namaMenu, Integer hargaMenu){
        this.nomorMenu = nomorMenu;
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
    }
}