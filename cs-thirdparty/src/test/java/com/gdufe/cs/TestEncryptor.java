package com.gdufe.cs;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/8 20:03
 **/
@SpringBootTest
public class TestEncryptor {
    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    public void test(){

       String accessKeyID = stringEncryptor.decrypt("RKrPF1Wwi4JpN+OjfWffgY1ao/gNrr9a8f7FZtarOFIlTCIKbJTteHHROUuc6klsLqedtS1D0Mbg8hKEuPDPwA==");
        System.out.println(accessKeyID);
        String accessKeySecreat = stringEncryptor.decrypt("9IpKJc5hHGWDRsj8Hb/SfrJP+kY42Ywr3IEnAqdZwNLQxgSvQx0x/RZ3xdqS886nfzbPAekUjfMWQ+8D8o4G+A==");
        System.out.println(accessKeySecreat);

    }
}
