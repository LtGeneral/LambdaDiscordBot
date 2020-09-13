package bot.java.lambda.command.commands.fun;

import bot.java.lambda.command.CommandContext;
import bot.java.lambda.command.HelpCategory;
import bot.java.lambda.command.ICommand;
import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class BirdCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        WebUtils.ins.getJSONObject("http://apis.duncte123.me/animal/bird").async(
                (json) -> {
                    if(!json.get("success").asBoolean()){
                        channel.sendMessage("Something went wrong, try again later").queue();
                        System.out.println(json);
                        return;
                    }
                    final JsonNode data = json.get("data");
                    final String file = data.get("file").asText();

                    final EmbedBuilder embed = EmbedUtils.getDefaultEmbed()
                            .setImage(file);

                    channel.sendMessage(embed.build()).queue();
                }
        );
    }

    @Override
    public String getName() {
        return "bird";
    }

    @Override
    public String getHelp() {
        return "Gives random image of bird";
    }

    @Override
    public HelpCategory getHelpCategory() {
        return HelpCategory.FUN;
    }
}
