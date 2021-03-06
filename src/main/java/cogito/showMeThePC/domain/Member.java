package cogito.showMeThePC.domain;
import cogito.showMeThePC.domain.enumType.MemberStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(name = "member_seq")
public class Member extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq")
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member")
    private List<Setting> settings = new ArrayList<>();

    @Embedded
    private Address address;

    private String email;

    private boolean quoteState;

    public Member(){}
    public Member(String name, MemberStatus memberStatus, Address address, String email,String password){
        this.name=name;
        this.memberStatus=memberStatus;
        this.address=address;
        this.email=email;
        this.quoteState=false;
        this.password=password;
        super.setCreatedTime(LocalDateTime.now());
    }
    public static Member createMember(String name, MemberStatus memberStatus, Address address, String email,String password){
        Member member = new Member(name, memberStatus, address,  email, password);
        return member;
    }//createMember() end
}
