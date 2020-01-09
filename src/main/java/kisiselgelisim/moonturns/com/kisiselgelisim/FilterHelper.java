package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.widget.Filter;
import java.util.ArrayList;
import kisiselgelisim.moonturns.com.kisiselgelisim.Adapter.RVAdapter;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.RVData;

public class FilterHelper extends Filter {

    private ArrayList<RVData> rvData;
    private RVAdapter adapter;

    public FilterHelper(ArrayList<RVData> rvData, RVAdapter adapter) {
        this.rvData = rvData;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        String aranan = charSequence.toString().toLowerCase();

        ArrayList<RVData> eslesen = new ArrayList<>();

        FilterResults results = new FilterResults();

        for (RVData data : rvData) {

            String title = data.getTitle().toLowerCase();

            if (title.contains(aranan)) {

                eslesen.add(data);

            }

        }

        results.values = eslesen;
        results.count = eslesen.size();

        return results;

    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapter.setFilter((ArrayList<RVData>) filterResults.values);
        adapter.notifyDataSetChanged();

    }
}
