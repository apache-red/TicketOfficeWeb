package by.htp.ticketoffice.entity;

import com.mysql.fabric.xmlrpc.base.Data;

import java.util.PriorityQueue;
import java.util.Queue;

public class Route {

    private int id;
    private String name;
    private Data data;
    private Queue routeQueue = new PriorityQueue();
    private Station station;




    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue getRouteQueue() {
        return routeQueue;
    }

    public void setRouteQueue(Queue routeQueue) {
        this.routeQueue = routeQueue;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
