package at.ubisoft.odv.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.manager.LocationManager;

public class Warp
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("warp")) {
      if (sender instanceof Player) {
        
        Player p = (Player)sender;
        if (args.length == 1) {
          
          if (LocationManager.cfg.getString(args[0]) != null) {
            
            p.teleport(LocationManager.getConfigLocation(args[0], LocationManager.cfg));
            p.sendMessage("§eItsLoru §8| §aDu bist nun bei §e" + args[0] + "§a.");
          
          }
          else {
            
            p.sendMessage("§eItsLoru §8| §cDieser Warp existiert nicht!");
          } 
        } else {
          
          p.sendMessage("§eItsLoru §8| §cSyntax: /warp [Name]");
        }
      
      } else {
        
        sender.sendMessage("§eItsLoru §8| §cNope.");
      } 
    }
    return false;
  }
}
