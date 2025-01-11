package com.thws.eventmanager.application.database.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "ticketCount", nullable = false)
    private long ticketCount;
    @Column(name = "ticketsSold", nullable = false)
    private long ticketsSold;
    @Column(name = "maxTicketsPerUser", nullable = false)
    private int maxTicketsPerUser;
    @OneToMany
    private List<UserEntity> artists;
    @OneToOne
    private EventLocationEntity location;
    @OneToMany
    private List<UserEntity> blockList; //List of User IDs that are blocked from buying tickets for this event

    public EventEntity(long id, String name, String description, long ticketCount, long ticketsSold, int maxTicketsPerUser, List<UserEntity> artists, EventLocationEntity location, List<UserEntity> blockList) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.ticketCount = ticketCount;
        this.ticketsSold = ticketsSold;
        this.maxTicketsPerUser = maxTicketsPerUser;
        this.artists = artists;
        this.location = location;
        this.blockList= blockList;
    }
    public EventEntity(){
    this.artists= new ArrayList<>();
    this.blockList= new ArrayList<>();
    }


    public EventLocationEntity getLocation() {
        return location;
    }
    public void setLocation(EventLocationEntity location) {
        this.location = location;
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

    public LocalDateTime getStartDate() { return startDate; }

    public LocalDateTime getEndDate() { return endDate; }

    public long getTicketCount() {
        return ticketCount;
    }

    public long getTicketsSold() {
        return ticketsSold;
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

    /*public void setArtists(User[] artists) {
    public void setArtists(List<Long> artists) {
        this.artists = artists;
    }*/

    /*public void addArtists(User artist){
        //if artist is not an artist, throw exception
    public void addArtists(User artist){
        if(artist.getPermission()!=Permission.ARTIST){
            throw new RuntimeException("Only artists can be added to an event");  //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        }
        else {
            artists.add(artist.getId());
        }
    }*/
  /*  public void removeArtists(User artist){
        if(artist.getPermission()!=Permission.ARTIST){
            throw new RuntimeException("Only artists can be removed from an event");  //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        }
        else if(artists.contains(artist.getId())){
            artists.remove(artist.getId());
        }
        else{
            throw new RuntimeException("Artist not found in event");  //hier vielleicht eher eine neue Exception-Klasse erstellen?!
        }
    }*/

    public void addBlockedUser(UserEntity userEntity){
        if(blockList==null){
            blockList=List.of(userEntity);
        }
        else{
            blockList.add(userEntity);
        }
    }
    public boolean removeBlockedUser(UserEntity userEntity){
        if(blockList==null){
            return false;
        }
        else{
            return blockList.remove(userEntity.getId());
        }
    }
    public boolean isBlocked(UserEntity userEntity){
        if(blockList==null){
            return false;
        }
        else{
            return blockList.contains(userEntity.getId());
        }
    }
    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public EventLocationEntity getEventLocation() {
        return location;
    }
}
