package co.edu.unal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clothing_items")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"receivedAt"}, 
        allowGetters = true)
public class ClothingItem implements Serializable{
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	  	@NotNull
	    private Integer room_id;
	    
	    @NotNull
	    private Boolean requires_washing;
	    
	    @NotNull
	    private Boolean requires_ironing;

	    @Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date requestedAt;
	    
	    private Date receivedAt;
	    
	    private Date deliveredAt;

	    public Boolean getRequires_washing() {
			return requires_washing;
		}

		public void setRequires_washing(Boolean requires_washing) {
			this.requires_washing = requires_washing;
		}

		public Boolean getRequires_ironing() {
			return requires_ironing;
		}

		public void setRequires_ironing(Boolean requires_ironing) {
			this.requires_ironing = requires_ironing;
		}


		public Date getRequestedAt() {
			return requestedAt;
		}

		public void setRequestedAt(Date requestedAt) {
			this.requestedAt = requestedAt;
		}

		public Date getDeliveredAt() {
			return deliveredAt;
		}

		public void setDeliveredAt(Date deliveredAt) {
			this.deliveredAt = deliveredAt;
		}

		public boolean hasBeenDelivered() {
			return this.deliveredAt != null;
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getRoomId() {
			return room_id;
		}

		public void setRoomId(Integer lot_id) {
			this.room_id = lot_id;
		}

		public Date getReceivedAt() {
			return receivedAt;
		}

		public void setReceivedAt(Date receivedAt) {
			this.receivedAt = receivedAt;
		}

}
