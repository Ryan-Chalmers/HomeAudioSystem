package ca.mcgill.ecse321.HomeAudioSystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.HomeAudioSystem.controller.TestHomeAudioSystemController;
import ca.mcgill.ecse321.HomeAudioSystem.persistence.TestPersistence;

@RunWith(Suite.class)
@SuiteClasses({ TestHomeAudioSystemController.class, TestPersistence.class })
public class AllTests {

}