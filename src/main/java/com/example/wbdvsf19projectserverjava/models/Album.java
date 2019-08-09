package com.example.wbdvsf19projectserverjava.models;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="albums")
public class Album {
	@Id
	private int id;
	private String name;

	public void set(Album newAlbum) {
        this.id = newAlbum.id;
        this.name = newAlbum.name;
	}

}
