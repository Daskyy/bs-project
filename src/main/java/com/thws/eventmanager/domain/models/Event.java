package com.thws.eventmanager.domain.models;
import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
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
    private List<Long> artists;
    @OneToOne
    private EventLocation location;
    @OneToMany
    private List<Long> blockList; //List of User IDs that are blocked from buying tickets for this event

    public Event(long id,String name, String description, long ticketCount, long ticketsSold, int maxTicketsPerUser, List<Long> artists, EventLocation location,List<Long> blockList) {
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
    public Event(){
    this.artists= new ArrayList<>();
    this.blockList= new ArrayList<>();
    }


    public EventLocation getLocation() {
        return location;
    }
    public void setLocation(EventLocation location) {
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

    public void addBlockedUser(User user){
        if(blockList==null){
            blockList=List.of(user.getId());
        }
        else{
            blockList.add(user.getId());
        }
    }
    public boolean removeBlockedUser(User user){
        if(blockList==null){
            return false;
        }
        else{
            return blockList.remove(user.getId());
        }
    }
    public boolean isBlocked(User user){
        if(blockList==null){
            return false;
        }
        else{
            return blockList.contains(user.getId());
        }
    }
    public void setMaxTicketsPerUser(int maxTicketsPerUser) {
        this.maxTicketsPerUser = maxTicketsPerUser;
    }

    public EventLocation getEventLocation() {
        return location;
    }
}
