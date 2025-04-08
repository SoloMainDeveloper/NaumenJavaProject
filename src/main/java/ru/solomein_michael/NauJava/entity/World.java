package ru.solomein_michael.NauJava.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;

@Entity
@Table(name = "worlds")
public class World {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Transient
    MapCell[][] map;

    String mapJson;

    public World(){
    }

    public World(MapCell[][] map){
        this.map = map;
        mapJson = new Gson().toJson(map);
    }

    @PostLoad
    private void initMapFromJson() {
        if (mapJson != null) {
            this.map = new Gson().fromJson(mapJson, MapCell[][].class);
        }
    }

    public String getMapJson() {
        return mapJson;
    }

    public void setMapJson(String mapJson) {
        this.mapJson = mapJson;
    }

    public Long getId() {
        return id;
    }

    public MapCell[][] getMap() {
        return map;
    }

    public void setMap(MapCell[][] map) {
        this.map = map;
    }
}
