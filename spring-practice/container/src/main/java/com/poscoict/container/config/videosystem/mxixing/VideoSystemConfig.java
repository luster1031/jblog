package com.poscoict.container.config.videosystem.mxixing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 
 * 
 * <------ JavaConfig1(DVDConfig) + JavaConfig(DVDPlayerConfig)
 * 	import
 * 
 * 
 * JavaConfig1 + JavaConfig2
 * 
 * 
 * 두개를 받아서 자기 설정에 넣는 것
 * 
 * 
 * */
@Configuration
@Import({DVDConfig.class, DVDPlayerConfig.class})
public class VideoSystemConfig {

}
