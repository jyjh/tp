package seedu.address.model.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityPathPair;

/**
 * Contains utility methods for populating {@code EntityReference} with sample data.
 */
public class SampleEntityUtil {

    private SampleEntityUtil() {} // Prevent instantiation

    /**
     * Returns a list of sample entities.
     * This contains every champion in League of Legends, as of 6/4/2026.
     * All images (if downloaded) are taken from the Official League of Legends Data Dragon.
     */
    public static List<EntityPathPair> getSampleEntities() {
        List<EntityPathPair> entities = new ArrayList<>();
        entities.add(new EntityPathPair(new Entity("Aatrox"), Path.of("images/aatrox.jpg")));
        entities.add(new EntityPathPair(new Entity("Ahri"), Path.of("images/ahri.jpg")));
        entities.add(new EntityPathPair(new Entity("Akali"), Path.of("images/akali.jpg")));
        entities.add(new EntityPathPair(new Entity("Ambessa"), Path.of("images/ambessa.jpg")));
        entities.add(new EntityPathPair(new Entity("Akshan"), Path.of("images/akshan.jpg")));
        entities.add(new EntityPathPair(new Entity("Alistar"), Path.of("images/alistar.jpg")));
        entities.add(new EntityPathPair(new Entity("Amumu"), Path.of("images/amumu.jpg")));
        entities.add(new EntityPathPair(new Entity("Anivia"), Path.of("images/anivia.jpg")));
        entities.add(new EntityPathPair(new Entity("Annie"), Path.of("images/annie.jpg")));
        entities.add(new EntityPathPair(new Entity("Aphelios"), Path.of("images/aphelios.jpg")));
        entities.add(new EntityPathPair(new Entity("Ashe"), Path.of("images/ashe.jpg")));
        entities.add(new EntityPathPair(new Entity("Aurelion Sol"), Path.of("images/aurelionsol.jpg")));
        entities.add(new EntityPathPair(new Entity("Aurora"), Path.of("images/aurora.jpg")));
        entities.add(new EntityPathPair(new Entity("Azir"), Path.of("images/azir.jpg")));
        entities.add(new EntityPathPair(new Entity("Bard"), Path.of("images/bard.jpg")));
        entities.add(new EntityPathPair(new Entity("Briar"), Path.of("images/briar.jpg")));
        entities.add(new EntityPathPair(new Entity("Bel'Veth"), Path.of("images/belveth.jpg")));
        entities.add(new EntityPathPair(new Entity("Blitzcrank"), Path.of("images/blitzcrank.jpg")));
        entities.add(new EntityPathPair(new Entity("Brand"), Path.of("images/brand.jpg")));
        entities.add(new EntityPathPair(new Entity("Braum"), Path.of("images/braum.jpg")));
        entities.add(new EntityPathPair(new Entity("Caitlyn"), Path.of("images/caitlyn.jpg")));
        entities.add(new EntityPathPair(new Entity("Camille"), Path.of("images/camille.jpg")));
        entities.add(new EntityPathPair(new Entity("Cassiopeia"), Path.of("images/cassiopeia.jpg")));
        entities.add(new EntityPathPair(new Entity("Cho'Gath"), Path.of("images/chogath.jpg")));
        entities.add(new EntityPathPair(new Entity("Corki"), Path.of("images/corki.jpg")));
        entities.add(new EntityPathPair(new Entity("Darius"), Path.of("images/darius.jpg")));
        entities.add(new EntityPathPair(new Entity("Diana"), Path.of("images/diana.jpg")));
        entities.add(new EntityPathPair(new Entity("Draven"), Path.of("images/draven.jpg")));
        entities.add(new EntityPathPair(new Entity("Dr. Mundo"), Path.of("images/drmundo.jpg")));
        entities.add(new EntityPathPair(new Entity("Ekko"), Path.of("images/ekko.jpg")));
        entities.add(new EntityPathPair(new Entity("Elise"), Path.of("images/elise.jpg")));
        entities.add(new EntityPathPair(new Entity("Evelynn"), Path.of("images/evelynn.jpg")));
        entities.add(new EntityPathPair(new Entity("Ezreal"), Path.of("images/ezreal.jpg")));
        entities.add(new EntityPathPair(new Entity("Fiddlesticks"), Path.of("images/fiddlesticks.jpg")));
        entities.add(new EntityPathPair(new Entity("Fiora"), Path.of("images/fiora.jpg")));
        entities.add(new EntityPathPair(new Entity("Fizz"), Path.of("images/fizz.jpg")));
        entities.add(new EntityPathPair(new Entity("Galio"), Path.of("images/galio.jpg")));
        entities.add(new EntityPathPair(new Entity("Gangplank"), Path.of("images/gangplank.jpg")));
        entities.add(new EntityPathPair(new Entity("Garen"), Path.of("images/garen.jpg")));
        entities.add(new EntityPathPair(new Entity("Gnar"), Path.of("images/gnar.jpg")));
        entities.add(new EntityPathPair(new Entity("Gragas"), Path.of("images/gragas.jpg")));
        entities.add(new EntityPathPair(new Entity("Graves"), Path.of("images/graves.jpg")));
        entities.add(new EntityPathPair(new Entity("Gwen"), Path.of("images/gwen.jpg")));
        entities.add(new EntityPathPair(new Entity("Hecarim"), Path.of("images/hecarim.jpg")));
        entities.add(new EntityPathPair(new Entity("Hwei"), Path.of("images/hwei.jpg")));
        entities.add(new EntityPathPair(new Entity("Heimerdinger"), Path.of("images/heimerdinger.jpg")));
        entities.add(new EntityPathPair(new Entity("Illaoi"), Path.of("images/illaoi.jpg")));
        entities.add(new EntityPathPair(new Entity("Irelia"), Path.of("images/irelia.jpg")));
        entities.add(new EntityPathPair(new Entity("Ivern"), Path.of("images/ivern.jpg")));
        entities.add(new EntityPathPair(new Entity("Janna"), Path.of("images/janna.jpg")));
        entities.add(new EntityPathPair(new Entity("Jarvan IV"), Path.of("images/jarvaniv.jpg")));
        entities.add(new EntityPathPair(new Entity("Jax"), Path.of("images/jax.jpg")));
        entities.add(new EntityPathPair(new Entity("Jayce"), Path.of("images/jayce.jpg")));
        entities.add(new EntityPathPair(new Entity("Jhin"), Path.of("images/jhin.jpg")));
        entities.add(new EntityPathPair(new Entity("Jinx"), Path.of("images/jinx.jpg")));
        entities.add(new EntityPathPair(new Entity("Kai'Sa"), Path.of("images/kaisa.jpg")));
        entities.add(new EntityPathPair(new Entity("Kalista"), Path.of("images/kalista.jpg")));
        entities.add(new EntityPathPair(new Entity("Karma"), Path.of("images/karma.jpg")));
        entities.add(new EntityPathPair(new Entity("Karthus"), Path.of("images/karthus.jpg")));
        entities.add(new EntityPathPair(new Entity("Kassadin"), Path.of("images/kassadin.jpg")));
        entities.add(new EntityPathPair(new Entity("Katarina"), Path.of("images/katarina.jpg")));
        entities.add(new EntityPathPair(new Entity("Kayle"), Path.of("images/kayle.jpg")));
        entities.add(new EntityPathPair(new Entity("Kayn"), Path.of("images/kayn.jpg")));
        entities.add(new EntityPathPair(new Entity("Kennen"), Path.of("images/kennen.jpg")));
        entities.add(new EntityPathPair(new Entity("Kha'Zix"), Path.of("images/khazix.jpg")));
        entities.add(new EntityPathPair(new Entity("K'Sante"), Path.of("images/ksante.jpg")));
        entities.add(new EntityPathPair(new Entity("Kindred"), Path.of("images/kindred.jpg")));
        entities.add(new EntityPathPair(new Entity("Kled"), Path.of("images/kled.jpg")));
        entities.add(new EntityPathPair(new Entity("Kog'Maw"), Path.of("images/kogmaw.jpg")));
        entities.add(new EntityPathPair(new Entity("LeBlanc"), Path.of("images/leblanc.jpg")));
        entities.add(new EntityPathPair(new Entity("Lee Sin"), Path.of("images/leesin.jpg")));
        entities.add(new EntityPathPair(new Entity("Leona"), Path.of("images/leona.jpg")));
        entities.add(new EntityPathPair(new Entity("Lillia"), Path.of("images/lillia.jpg")));
        entities.add(new EntityPathPair(new Entity("Lissandra"), Path.of("images/lissandra.jpg")));
        entities.add(new EntityPathPair(new Entity("Lucian"), Path.of("images/lucian.jpg")));
        entities.add(new EntityPathPair(new Entity("Lulu"), Path.of("images/lulu.jpg")));
        entities.add(new EntityPathPair(new Entity("Lux"), Path.of("images/lux.jpg")));
        entities.add(new EntityPathPair(new Entity("Malphite"), Path.of("images/malphite.jpg")));
        entities.add(new EntityPathPair(new Entity("Malzahar"), Path.of("images/malzahar.jpg")));
        entities.add(new EntityPathPair(new Entity("Maokai"), Path.of("images/maokai.jpg")));
        entities.add(new EntityPathPair(new Entity("Master Yi"), Path.of("images/masteryi.jpg")));
        entities.add(new EntityPathPair(new Entity("Mel"), Path.of("images/mel.jpg")));
        entities.add(new EntityPathPair(new Entity("Miss Fortune"), Path.of("images/missfortune.jpg")));
        entities.add(new EntityPathPair(new Entity("Milio"), Path.of("images/milio.jpg")));
        entities.add(new EntityPathPair(new Entity("Mordekaiser"), Path.of("images/mordekaiser.jpg")));
        entities.add(new EntityPathPair(new Entity("Morgana"), Path.of("images/morgana.jpg")));
        entities.add(new EntityPathPair(new Entity("Nami"), Path.of("images/nami.jpg")));
        entities.add(new EntityPathPair(new Entity("Naafiri"), Path.of("images/naafiri.jpg")));
        entities.add(new EntityPathPair(new Entity("Nasus"), Path.of("images/nasus.jpg")));
        entities.add(new EntityPathPair(new Entity("Nautilus"), Path.of("images/nautilus.jpg")));
        entities.add(new EntityPathPair(new Entity("Neeko"), Path.of("images/neeko.jpg")));
        entities.add(new EntityPathPair(new Entity("Nidalee"), Path.of("images/nidalee.jpg")));
        entities.add(new EntityPathPair(new Entity("Nilah"), Path.of("images/nilah.jpg")));
        entities.add(new EntityPathPair(new Entity("Nocturne"), Path.of("images/nocturne.jpg")));
        entities.add(new EntityPathPair(new Entity("Nunu & Willump"), Path.of("images/nunu.jpg")));
        entities.add(new EntityPathPair(new Entity("Olaf"), Path.of("images/olaf.jpg")));
        entities.add(new EntityPathPair(new Entity("Orianna"), Path.of("images/orianna.jpg")));
        entities.add(new EntityPathPair(new Entity("Ornn"), Path.of("images/ornn.jpg")));
        entities.add(new EntityPathPair(new Entity("Pantheon"), Path.of("images/pantheon.jpg")));
        entities.add(new EntityPathPair(new Entity("Poppy"), Path.of("images/poppy.jpg")));
        entities.add(new EntityPathPair(new Entity("Pyke"), Path.of("images/pyke.jpg")));
        entities.add(new EntityPathPair(new Entity("Qiyana"), Path.of("images/qiyana.jpg")));
        entities.add(new EntityPathPair(new Entity("Quinn"), Path.of("images/quinn.jpg")));
        entities.add(new EntityPathPair(new Entity("Rakan"), Path.of("images/rakan.jpg")));
        entities.add(new EntityPathPair(new Entity("Rammus"), Path.of("images/rammus.jpg")));
        entities.add(new EntityPathPair(new Entity("Rek'Sai"), Path.of("images/reksai.jpg")));
        entities.add(new EntityPathPair(new Entity("Rell"), Path.of("images/rell.jpg")));
        entities.add(new EntityPathPair(new Entity("Renata Glasc"), Path.of("images/renataglasc.jpg")));
        entities.add(new EntityPathPair(new Entity("Renekton"), Path.of("images/renekton.jpg")));
        entities.add(new EntityPathPair(new Entity("Rengar"), Path.of("images/rengar.jpg")));
        entities.add(new EntityPathPair(new Entity("Riven"), Path.of("images/riven.jpg")));
        entities.add(new EntityPathPair(new Entity("Rumble"), Path.of("images/rumble.jpg")));
        entities.add(new EntityPathPair(new Entity("Ryze"), Path.of("images/ryze.jpg")));
        entities.add(new EntityPathPair(new Entity("Samira"), Path.of("images/samira.jpg")));
        entities.add(new EntityPathPair(new Entity("Smolder"), Path.of("images/smolder.jpg")));
        entities.add(new EntityPathPair(new Entity("Sejuani"), Path.of("images/sejuani.jpg")));
        entities.add(new EntityPathPair(new Entity("Senna"), Path.of("images/senna.jpg")));
        entities.add(new EntityPathPair(new Entity("Seraphine"), Path.of("images/seraphine.jpg")));
        entities.add(new EntityPathPair(new Entity("Sett"), Path.of("images/sett.jpg")));
        entities.add(new EntityPathPair(new Entity("Shaco"), Path.of("images/shaco.jpg")));
        entities.add(new EntityPathPair(new Entity("Shen"), Path.of("images/shen.jpg")));
        entities.add(new EntityPathPair(new Entity("Shyvana"), Path.of("images/shyvana.jpg")));
        entities.add(new EntityPathPair(new Entity("Singed"), Path.of("images/singed.jpg")));
        entities.add(new EntityPathPair(new Entity("Sion"), Path.of("images/sion.jpg")));
        entities.add(new EntityPathPair(new Entity("Sivir"), Path.of("images/sivir.jpg")));
        entities.add(new EntityPathPair(new Entity("Skarner"), Path.of("images/skarner.jpg")));
        entities.add(new EntityPathPair(new Entity("Sona"), Path.of("images/sona.jpg")));
        entities.add(new EntityPathPair(new Entity("Soraka"), Path.of("images/soraka.jpg")));
        entities.add(new EntityPathPair(new Entity("Swain"), Path.of("images/swain.jpg")));
        entities.add(new EntityPathPair(new Entity("Sylas"), Path.of("images/sylas.jpg")));
        entities.add(new EntityPathPair(new Entity("Syndra"), Path.of("images/syndra.jpg")));
        entities.add(new EntityPathPair(new Entity("Tahm Kench"), Path.of("images/tahmkench.jpg")));
        entities.add(new EntityPathPair(new Entity("Taliyah"), Path.of("images/taliyah.jpg")));
        entities.add(new EntityPathPair(new Entity("Talon"), Path.of("images/talon.jpg")));
        entities.add(new EntityPathPair(new Entity("Taric"), Path.of("images/taric.jpg")));
        entities.add(new EntityPathPair(new Entity("Teemo"), Path.of("images/teemo.jpg")));
        entities.add(new EntityPathPair(new Entity("Thresh"), Path.of("images/thresh.jpg")));
        entities.add(new EntityPathPair(new Entity("Tristana"), Path.of("images/tristana.jpg")));
        entities.add(new EntityPathPair(new Entity("Trundle"), Path.of("images/trundle.jpg")));
        entities.add(new EntityPathPair(new Entity("Tryndamere"), Path.of("images/tryndamere.jpg")));
        entities.add(new EntityPathPair(new Entity("Twisted Fate"), Path.of("images/twistedfate.jpg")));
        entities.add(new EntityPathPair(new Entity("Twitch"), Path.of("images/twitch.jpg")));
        entities.add(new EntityPathPair(new Entity("Udyr"), Path.of("images/udyr.jpg")));
        entities.add(new EntityPathPair(new Entity("Urgot"), Path.of("images/urgot.jpg")));
        entities.add(new EntityPathPair(new Entity("Varus"), Path.of("images/varus.jpg")));
        entities.add(new EntityPathPair(new Entity("Vayne"), Path.of("images/vayne.jpg")));
        entities.add(new EntityPathPair(new Entity("Vex"), Path.of("images/vex.jpg")));
        entities.add(new EntityPathPair(new Entity("Veigar"), Path.of("images/veigar.jpg")));
        entities.add(new EntityPathPair(new Entity("Vel'Koz"), Path.of("images/velkoz.jpg")));
        entities.add(new EntityPathPair(new Entity("Vi"), Path.of("images/vi.jpg")));
        entities.add(new EntityPathPair(new Entity("Viego"), Path.of("images/viego.jpg")));
        entities.add(new EntityPathPair(new Entity("Viktor"), Path.of("images/viktor.jpg")));
        entities.add(new EntityPathPair(new Entity("Vladimir"), Path.of("images/vladimir.jpg")));
        entities.add(new EntityPathPair(new Entity("Volibear"), Path.of("images/volibear.jpg")));
        entities.add(new EntityPathPair(new Entity("Warwick"), Path.of("images/warwick.jpg")));
        entities.add(new EntityPathPair(new Entity("Wukong"), Path.of("images/wukong.jpg")));
        entities.add(new EntityPathPair(new Entity("Xayah"), Path.of("images/xayah.jpg")));
        entities.add(new EntityPathPair(new Entity("Xerath"), Path.of("images/xerath.jpg")));
        entities.add(new EntityPathPair(new Entity("Xin Zhao"), Path.of("images/xinzhao.jpg")));
        entities.add(new EntityPathPair(new Entity("Yasuo"), Path.of("images/yasuo.jpg")));
        entities.add(new EntityPathPair(new Entity("Yone"), Path.of("images/yone.jpg")));
        entities.add(new EntityPathPair(new Entity("Yorick"), Path.of("images/yorick.jpg")));
        entities.add(new EntityPathPair(new Entity("Yunara"), Path.of("images/yunara.jpg")));
        entities.add(new EntityPathPair(new Entity("Yuumi"), Path.of("images/yuumi.jpg")));
        entities.add(new EntityPathPair(new Entity("Zac"), Path.of("images/zac.jpg")));
        entities.add(new EntityPathPair(new Entity("Zaahen"), Path.of("images/zaahen.jpg")));
        entities.add(new EntityPathPair(new Entity("Zed"), Path.of("images/zed.jpg")));
        entities.add(new EntityPathPair(new Entity("Zeri"), Path.of("images/zeri.jpg")));
        entities.add(new EntityPathPair(new Entity("Ziggs"), Path.of("images/ziggs.jpg")));
        entities.add(new EntityPathPair(new Entity("Zilean"), Path.of("images/zilean.jpg")));
        entities.add(new EntityPathPair(new Entity("Zoe"), Path.of("images/zoe.jpg")));
        entities.add(new EntityPathPair(new Entity("Zyra"), Path.of("images/zyra.jpg")));

        return entities;
    }
}
