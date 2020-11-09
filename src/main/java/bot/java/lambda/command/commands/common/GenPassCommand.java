package bot.java.lambda.command.commands.common;

import bot.java.lambda.command.CommandContext;
import bot.java.lambda.command.HelpCategory;
import bot.java.lambda.command.ICommand;
import bot.java.lambda.config.Config;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.Random;

public class GenPassCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final List<String> args = ctx.getArgs();

        if (args.isEmpty()) {
            channel.sendMessage("Missing Arguments").queue();
            return;
        }

        try {
            int len = Integer.parseInt(args.get(0));

            if (len >= 51) {
                ctx.getMessage().addReaction("❌").queue();
                channel.sendMessage("You don't need password exceeding 50 <:Wot:755715077029625916>").queue();
                return;
            }

            StringBuilder password = new StringBuilder();
            char ch;
            Random random = new Random();

            for (int i = 1; i <= len; i++) {
                ch = (char) (Math.abs(random.nextInt()) % 93 + 33);
                password.append(ch);
            }

            try {
                if (args.get(1).equalsIgnoreCase("DM")) {
                    ctx.getMessage().addReaction("✅").queue();
                    ctx.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Here's your Pass - \n```" + password.toString() + "```").queue());
                    channel.sendMessage("Sent you a DM").queue();
                } else {
                    ctx.getMessage().addReaction("✅").queue();
                    channel.sendMessage("Here's your Pass - \n``` " + password.toString() + " ```").queue();
                }
            } catch (IndexOutOfBoundsException e) {
                ctx.getMessage().addReaction("✅").queue();
                channel.sendMessage("Here's your Pass - \n``` " + password.toString() + " ```").queue();
            }
        } catch (NumberFormatException e) {
            e.fillInStackTrace();
            ctx.getMessage().addReaction("❌").queue();
            channel.sendMessage("Enter a number to specify the length !! ").queue();
        }
    }

    @Override
    public String getName() {
        return "genpass";
    }

    @Override
    public String getHelp() {
        return "Generate Random password for you\n" +
                "Usage : " + Config.get("prefix") + "genPass <length>\n" +
                "        " + Config.get("prefix") + "genPass <length> dm \n" +
                "Aliases : " + getAliases();
    }

    @Override
    public HelpCategory getHelpCategory() {
        return HelpCategory.COM;
    }

    @Override
    public List<String> getAliases() {
        return List.of("generatePass", "password", "genPass");
    }

}
