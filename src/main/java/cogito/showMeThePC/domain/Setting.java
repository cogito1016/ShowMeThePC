package cogito.showMeThePC.domain;
import cogito.showMeThePC.domain.device.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(name = "setting_seq")
public class Setting extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "setting_seq")
    @Column(name = "setting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "setting",cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

    public Setting(){}
    public Setting(Member member, Game game, Device... devices){
        this.member=member;
        this.game=game;
        for(Device device:devices){
            this.devices.add(device);
        }//for end
        super.setCreatedTime(LocalDateTime.now());
    }
    public static Setting createSetting(Member member, Game game, Device... devices){
        Setting setting = new Setting(member,game,devices);
        return setting;
    }//createSeeting() end

}
