package com.thws.eventmanager.domain.models;

public class Event {
    private long id;
    private String name;
    private String description;
    private long ticketCount;
    private long ticketsSold;
    private int maxTicketsPerUser;
    private User[] artists;
    private EventLocation location;


    public Event(long id,String name, String description, long ticketCount, long ticketsSold, int maxTicketsPerUser, User[] artists, EventLocation location) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.ticketCount = ticketCount;
        this.ticketsSold = ticketsSold;
        this.maxTicketsPerUser = maxTicketsPerUser;
        this.artists = artists;
        this.location = location;
    }

    public EventLocation getLocation() {
        return location;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }

    public Event(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public User[] getArtists() {
        return artists;
    }

    public int getMaxTicketsPerUser() {
        return maxTicketsPerUser;
    }

    public boolean isSoldOut() {
        return ticketCount == ticketsSold;
    }

    public boolean isAvailable() {
        return ticketCount > ticketsSold;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public void setArtists(User[] artists) {
        this.artists = artists;
    }

    public void addArtists(User artist){
        //if artist is not an artist, throw exception
        if(artist.getPermission()!=Permission.ARTIST){
            throw new RuntimeException("Only artists can be added to an event");  //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        }
        else {
            //if Artist Array is empty, create new Array with size 1 and add artist
            if(artists==null){
                artists= new User[1];
                artists[0]=artist;
            }
            //if Artist Array is not empty, create new Array with size+1 and add artist
            else {
                User[] newArtists = new User[artists.length + 1];
                for (int i = 0; i < artists.length; i++) {
                    newArtists[i] = artists[i];
                }
                newArtists[artists.length] = artist;
                artists = newArtists;
            }
        }
    }
    public void removeArtists(User artist){
        if(artist.getPermission()!=Permission.ARTIST){
            throw new RuntimeException("Only artists can be removed from an event");  //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        }

        else {
            boolean found=false;
            User[] newArtists = new User[artists.length - 1];
            int index = 0;
            for (User a : artists) {
                if (a.getId() != artist.getId()) {
                    newArtists[index++] = a;
                }
                else{found=true;}
            }
            if(!found) throw new RuntimeException("Artist not found in event");
            artists = newArtists;
        }
    }

    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }
}
