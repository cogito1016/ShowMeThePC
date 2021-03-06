package cogito.showMeThePC.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@SequenceGenerator(name = "board_seq")
public class Board extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_seq")
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="setting_id")
    private Setting setting;

    @Lob
    private String content;

    private String title;

    public Board(){}
    public Board(Member member, Setting setting, String content,String title){
        this.member=member;
        this.setting=setting;
        this.content=content;
        this.title=title;
        super.setCreatedTime(LocalDateTime.now());
    }
    public static Board createBoard(Member member, Setting setting, String content,String title){
        Board board = new Board(member,setting,content,title);
        return board;
    }//createBoard() end

    //비지니스로직
    public boolean isSettingExist() {
        return setting!=null;
    }//isSettingExist9) end

    public int getTotalPrice() {
        if(!isSettingExist())
            return 0;
        return this.setting.getTotalPrice();
    }//getTotalPrice() end

}
