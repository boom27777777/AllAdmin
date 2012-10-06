package com.gravypod.AllAdmin;

import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;

public class Startup {
	
	AllAdmin plugin;
	
	public Startup(AllAdmin _plugin, CommandHandler ch) {
	
		plugin = _plugin;
		
		loadCommands(ch);
		
	}
	
	public void loadCommands(CommandHandler ch) {
	
		String classPath = "com.gravypod.AllAdmin.commands.";
		String packageName = classPath.replaceAll("\\.", "/");
		
		try {
			
			JarInputStream jarFile = new JarInputStream(new FileInputStream(plugin.jarLocation()));
			
			JarEntry jarEntry;
			
			while(true) {
				
				jarEntry = jarFile.getNextJarEntry();
				
				if (jarEntry == null) {
					break;
				}
				
				if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class")) && !(jarEntry.getName().contains("$"))) {
					try {
						// Let it snow, let it snow, let it snow!
						((ICommand) Class.forName(jarEntry.getName().replaceAll("/", "\\.").replace(".class", "")).newInstance()).registerSelf(plugin, ch);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}