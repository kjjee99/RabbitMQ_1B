package winirnd.yn21.rabbitmq.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SensorVO {
	private String id;
	private String gw;
	private String data;
	
	@Builder
	public SensorVO(String id, String gw, String data) {
		this.id = id;
		this.gw = gw;
		this.data = "d: " + data;
	}
}
