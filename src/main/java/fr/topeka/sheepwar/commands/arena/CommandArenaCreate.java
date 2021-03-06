package fr.topeka.sheepwar.commands.arena;

import fr.topeka.sheepwar.arena.Arena;
import org.bukkit.entity.Player;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.StateArena;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.CommandDeclaration;

@CommandDeclaration(
		command = "CREATE",
		usage = "/sw arena create <arena_name>",
		permission = "sheepwar.arena.create"
		)
public class CommandArenaCreate extends AbstractCommand {

	public CommandArenaCreate(SheepWar instance, Player player, String label, String[] args, Integer nArgs) {
		super(instance, player, label, args, nArgs);
	}

	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(!_instance._arenaList.containsKey(args[2])) {
				_instance._arenaList.put(args[2], new Arena(_instance, args[2], StateArena.MAINTENANCE));
				_instance._arenaList.get(args[2]).setLobbyLocation(player.getLocation());
				player.sendMessage("Arena " + args[2] + " created");
				return true;
			}
			player.sendMessage("Arena " + args[2] + " already exist");
			return true;
		}
		player.sendMessage("not enough arguments");
		return true;
	}

}
