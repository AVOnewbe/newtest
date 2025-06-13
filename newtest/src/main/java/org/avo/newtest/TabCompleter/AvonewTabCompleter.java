package org.avo.newtest.TabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvonewTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> subcommands = List.of("random", "randomore", "gui");
            List<String> matches = new ArrayList<>();

            String input = args[0].toLowerCase();
            for (String sub : subcommands) {
                if (sub.startsWith(input)) {
                    matches.add(sub);
                }
            }
            return matches;
        }
        return null;
    }
}
