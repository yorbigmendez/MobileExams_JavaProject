package access_data;

import java.util.ArrayList;

import questions.TrueFalse;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class TrueFalseRepository implements IRepository<TrueFalse> {

    @Override
    public boolean Save(TrueFalse o) {
        return false;
    }

    @Override
    public boolean Update(TrueFalse o) {
        return false;
    }

    @Override
    public boolean Delete(TrueFalse o) {
        return false;
    }

    @Override
    public ArrayList<TrueFalse> GetAll(int id) {
        return null;
    }

    @Override
    public ArrayList<TrueFalse> GetBy(TrueFalse o) {
        return null;
    }
}
