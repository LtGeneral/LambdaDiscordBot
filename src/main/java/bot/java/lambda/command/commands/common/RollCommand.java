/*
 * Copyright 2020 Zone-Infinity
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package bot.java.lambda.command.commands.common;

import bot.java.lambda.command.CommandContext;
import bot.java.lambda.command.HelpCategory;
import bot.java.lambda.command.ICommand;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RollCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Random random = new Random();
        ctx.getChannel().sendMessage("Rolling...").queue(
                message -> message.editMessage(
                        ""+(random.nextInt(6)+1)
                ).queueAfter(500, TimeUnit.MILLISECONDS)
        );
    }

    @Override
    public String getName() {
        return "roll";
    }

    @Override
    public String getHelp() {
        return "Rolls a dice for you";
    }

    @Override
    public HelpCategory getHelpCategory() {
        return HelpCategory.COM;
    }
}