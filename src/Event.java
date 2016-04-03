import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

public class Event {
	private String name;
	private String description;
	private Recurrence rec;
	private DateTime timeDue;
	private DateTime startTime;
	private Importance urgency;
	private String[] tags;
	private DateTime reminder;
	
	public Event(){
		urgency = Importance.UNSPECIFIED;
	}
	
	public Event(String nameString,String desc, Recurrence recurrence, DateTime dueTime, DateTime startingTime,
			String[] tagStrings, Importance urg){
		this.name = nameString;
		this.description = desc;
		this.rec = recurrence;
		this.timeDue = dueTime;
		this.startTime = startingTime;
		this.tags = tagStrings;
		this.urgency = urg;
	}
	
	public String timeUntilDue(){ // pattern DD:HH:MM
		double days = Days.daysBetween(DateTime.now().withTimeAtStartOfDay() , timeDue.withTimeAtStartOfDay() ).getDays();
		double hours = Hours.hoursBetween(DateTime.now() , timeDue).getHours();
		double minutes = Minutes.minutesBetween(DateTime.now() , timeDue).getMinutes();
		StringBuilder sb = new StringBuilder();
		if(days < 10.0){
			sb.append("0"+(int)days);
		}
		else{
			sb.append(String.valueOf(days).substring(0,2));
		}
		sb.append(":");
		if(hours < 10.0){
			sb.append("0"+(int)hours);
		}
		else{
			sb.append(String.valueOf(hours).substring(0,2));
		}
		sb.append(":");
		if(minutes < 10.0){
			sb.append("0"+(int)minutes);
		}
		else{
			sb.append(String.valueOf(minutes).substring(0,2));
		}
		return sb.toString();
		
	}
	
	public boolean inProgress(){
		if(startTime.isBeforeNow() && timeDue.isAfterNow())
			return true;
		
		return false;
	}
	
	public enum Importance{
		TRIVIAL,
		MINOR,
		MODERATE,
		MAJOR,
		URGENT,
		UNSPECIFIED
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Recurrence getRec() {
		return rec;
	}

	public void setRec(Recurrence rec) {
		this.rec = rec;
	}

	public DateTime getTimeDue() {
		return timeDue;
	}

	public void setTimeDue(DateTime timeDue) {
		this.timeDue = timeDue;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public Importance getUrgency() {
		return urgency;
	}

	public void setUrgency(Importance urgency) {
		this.urgency = urgency;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public DateTime getReminder() {
		return reminder;
	}

	public void setReminder(DateTime reminder) {
		this.reminder = reminder;
	}
	
}
