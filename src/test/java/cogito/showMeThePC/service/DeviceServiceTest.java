package cogito.showMeThePC.service;

import cogito.showMeThePC.domain.device.Device;
import cogito.showMeThePC.domain.enumType.DeviceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void 디바이스저장테스트(){

        Device processor = new Device();
        processor.setName("i5");
        processor.setPrice(50000);
        processor.setSite("www.naver.com");
        processor.setDeviceType(DeviceType.PROCESSOR);

        Long findId = deviceService.saveDevice(processor);

        assertEquals("",processor,deviceService.findOne(findId));

    }


}