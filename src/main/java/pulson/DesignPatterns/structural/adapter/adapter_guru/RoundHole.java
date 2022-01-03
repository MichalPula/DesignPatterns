package pulson.DesignPatterns.structural.adapter.adapter_guru;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoundHole {
    private double radius;
    public boolean fits(RoundBuildingBlock roundBuildingBlock) {
        return this.radius >= roundBuildingBlock.getRadius();
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class RoundBuildingBlock {
    private double radius;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class SquareBuildingBlock {
    private double width;
}



@AllArgsConstructor
class SquareToRoundBuildingBlockAdapter1 extends RoundBuildingBlock {
    private SquareBuildingBlock squareBB;
    @Override
    public double getRadius() {
        return Math.sqrt(Math.pow((this.squareBB.getWidth() / 2), 2) * 2);
    }
}

@AllArgsConstructor
class SquareToRoundBuildingBlockAdapter2 {
    private SquareBuildingBlock squareBB;
    public RoundBuildingBlock toRoundBuildingBlock() {
        double radius = Math.sqrt(Math.pow((this.squareBB.getWidth() / 2), 2) * 2);
        return new RoundBuildingBlock(radius);
    }
}


class Test {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundBuildingBlock roundBuildingBlock = new RoundBuildingBlock(5);
        if(hole.fits(roundBuildingBlock)){
            System.out.println("Round block r5 fits in hole r5");
        }


        SquareBuildingBlock squareBuildingBlock = new SquareBuildingBlock(7);
        //hole.fits(squareBuildingBlock);//Required type:RoundBuildingBlock - Provided:SquareBuildingBlock
        //Tutaj adapter wchodzi do gry

        SquareToRoundBuildingBlockAdapter1 adapter = new SquareToRoundBuildingBlockAdapter1(squareBuildingBlock);
        if(hole.fits(adapter)){
            System.out.println("Square block w=" + squareBuildingBlock.getWidth() + " fits in hole r=" + hole.getRadius());
        } else {
            System.out.println("Square block w=" + squareBuildingBlock.getWidth() + " DOESN'T fit in hole r=" + hole.getRadius());
        }

        //Dla r=5, maksymalny bok kwadratu opisanego na tym okręgu to 7.07
        SquareToRoundBuildingBlockAdapter2 adapter2 = new SquareToRoundBuildingBlockAdapter2(squareBuildingBlock);
        if (hole.fits(adapter2.toRoundBuildingBlock())) {
            System.out.println("Square block w=" + squareBuildingBlock.getWidth() + " fits in hole r=" + hole.getRadius());
        } else {
            System.out.println("Square block w=" + squareBuildingBlock.getWidth() + " DOESN'T fit in hole r=" + hole.getRadius());
        }//Square block w=7.0 fits in hole r=5.0
    }
}
