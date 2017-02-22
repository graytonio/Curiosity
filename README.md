# Curiosity
2017 MARS Robot

The JAVA code source for the 2017 FRC Robotics Competition Steamworks

-----Mapping IDs-----

--Talon SRX--
  Front Right Drive Talon SRX ID-10;
  Front Left Drive Talon SRX ID-11;
  Rear Right Drive Talon SRX ID-12;
  Rear Left Drive Talon SRX ID-13;
  
  Rope Climb Talon SRX ID-20;
	
  Ball Pickup Talon SRX ID-30;
	
--Solenoid--
	Ball Door A-0;
	Ball Door B-1;
	Lower Gear Piston A-2;
	Lower Gear Piston B-3;
	Upper Gear Piston A-4;
	Upper Gear Piston B-5;
	
--Joystick--
  Ball Lift Up Button = Stick 1, Button 2;
	Ball Lift Down Button = Stick 1, Button 3;
	Ball Pickup On Hold Button = Stick 0, Button 2;
	Gear Tray Up Button = Stick 1, Button 1;
	Gear Tray Down Button = Stick 1, Button 4;
	Spin Rope Climb Button = Stick 0, Button 1;
	Gear Arms Out Button = Stick 1, Button 5;
	Gear Arms In Button = Stick 1, Button 6;
	
--Constants--
	DISTANCE_PER_PULSE = (Math.PI*6)/(240*4);
