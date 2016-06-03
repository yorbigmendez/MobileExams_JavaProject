package access_data;

import java.util.ArrayList;

import questions.SingleSelection;

/**
 * Created by Mendez Soto on 5/26/2016.
 */
public class SingleSelectionRepository implements IRepository<SingleSelection> {

    @Override
    public boolean Save(SingleSelection o) {
        return false;
    }

    @Override
    public boolean Update(SingleSelection o) {
        return false;
    }

    @Override
    public boolean Delete(SingleSelection o) {
        return false;
    }

    @Override
    public ArrayList<SingleSelection> GetAll(int i) {
        return null;
    }

    @Override
    public ArrayList<SingleSelection> GetBy(SingleSelection o) {
        return null;
    }
}
