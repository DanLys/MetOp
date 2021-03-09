package method.optimisation.base_control;

import lombok.Setter;
import method.optimisation.attributes.MethodsType;
import method.optimisation.data.brent.CombinationOfBrentService;
import method.optimisation.data.dichotomy.DichotomyService;
import method.optimisation.data.fibonacchi.FibonachiService;
import method.optimisation.data.goldenratio.GoldenRatioService;
import method.optimisation.data.parabola.ParabolaService;
import method.optimisation.exceptions.method_types.ApiMethodNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Danil Lyskin at 16:15 25.02.2021
 */

@Service
@Transactional
@Setter
public class BaseService {
    
    private MethodsType type;

    private final DichotomyService dichotomyService;
    private final FibonachiService fibonachiService;
    private final ParabolaService parabolaService;
    private final GoldenRatioService goldenRatioService;
    private final CombinationOfBrentService combinationOfBrentService;

    public BaseService(final DichotomyService dichotomyService,
                       final FibonachiService fibonachiService,
                       final ParabolaService parabolaService,
                       final GoldenRatioService goldenRatioService,
                       final CombinationOfBrentService combinationOfBrentService) {
        this.fibonachiService = fibonachiService;
        this.parabolaService = parabolaService;
        this.goldenRatioService = goldenRatioService;
        this.combinationOfBrentService = combinationOfBrentService;
        type = MethodsType.DICHOTOMY;
        this.dichotomyService = dichotomyService;
    }

    public Double solve(double a, double b, int iterator) {
        switch (type) {
            case DICHOTOMY: return dichotomyService.findResult(a, b, iterator);
            case FIBONACHI: return fibonachiService.findResult(a, b, iterator);
            case PARABOLA: return parabolaService.findResult(a, b, iterator);
            case GOLDENRATIO: return goldenRatioService.findResult(a, b, iterator);
            case BRENT: return combinationOfBrentService.findResult(a, b, iterator);
        }

        throw new ApiMethodNotFoundException(String.format("Method %s does not exist", type.getType()));
    }

}
