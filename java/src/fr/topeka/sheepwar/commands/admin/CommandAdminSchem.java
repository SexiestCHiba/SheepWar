package fr.topeka.sheepwar.commands.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;

import fr.topeka.sheepwar.SheepWar;
import fr.topeka.sheepwar.arena.Arena;
import fr.topeka.sheepwar.commands.AbstractCommand;
import fr.topeka.sheepwar.commands.Permission;
/**
 * 
 * @see https://worldedit.enginehub.org/en/latest/api/examples/clipboard/
 */
public class CommandAdminSchem extends AbstractCommand {

	public CommandAdminSchem(SheepWar instance, Player player, String label, String[] args, int nArgs) {
		super(instance, player, label, args, nArgs);
		// TODO Auto-generated constructor stub
	}

	@Permission(_permission = "sheepwar.admin.schem")
	@Override
	public boolean handle() {
		if(nArgs > 2) {
			if(_instance._arenaList.containsKey(args[2])) {
				Arena a = _instance._arenaList.get(args[2]);
				try {
					Region selection = _instance.WE.getSession(player).getSelection(BukkitAdapter.adapt(player.getWorld()));
					if(selection instanceof CuboidRegion) {
						CuboidRegion region = (CuboidRegion) selection;
						BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
						EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(player.getWorld()), -1);
						ForwardExtentCopy forwardextendcopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
						Operations.complete(forwardextendcopy);
						File file = new File(_instance.getDataFolder() + File.separator + a._Name + ".schem");
						ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file));
						writer.write(clipboard);
					}
				} catch (WorldEditException | IOException e) {
					_instance.getLogger().warning(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}