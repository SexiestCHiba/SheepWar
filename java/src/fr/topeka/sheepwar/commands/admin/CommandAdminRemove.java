package fr.topeka.sheepwar.commands.admin;

import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;

public class CommandAdminRemove extends AbstractCommand {

	public CommandAdminRemove(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.admin.remove")
	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				_instance._arenaList.remove(args[2]);
				return true;
			}else {
				player.sendMessage("Unable to found this named arena");
				return true;
			}
		}
		player.sendMessage("No enough arguments");
		return true;
	}

}