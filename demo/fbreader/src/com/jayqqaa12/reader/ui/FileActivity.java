package com.jayqqaa12.reader.ui;

import java.io.File;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.ViewById;
import org.geometerplus.android.fbreader.FBReader;

import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.common.T;
import com.jayqqaa12.reader.R;
import com.jayqqaa12.reader.engine.FileEngine;
import com.jayqqaa12.reader.model.Book;
import com.jayqqaa12.reader.model.BookFile;
import com.jayqqaa12.reader.model.Group;
import com.jayqqaa12.reader.ui.adapter.FileAdapter;
import com.jayqqaa12.reader.ui.adapter.itemview.BookItemView;

@EActivity(R.layout.activity_file)
public class FileActivity extends BaseActivity implements OnChildClickListener, OnItemLongClickListener
{
	@ViewById
	ExpandableListView elv;
	@ViewById
	TextView tv;
	@ViewById
	ImageView iv_empty;

	@Bean
	FileEngine engine;
	@Bean
	FileAdapter adapter;
	private AbaseDao db = AbaseDao.create();
	private String parentFile;

	@AfterViews
	public void init()
	{
		elv.setAdapter(adapter);
		elv.setGroupIndicator(null);
		setFileView(Environment.getExternalStorageDirectory().getPath());
		initHeadView(R.string.input_book);
		iv_head_right.setVisibility(View.GONE);
		elv.setOnChildClickListener(this);

		elv.setOnItemLongClickListener(this);
	}

	public void setFileView(String filePath)
	{
		parentFile = new File(filePath).getParent();
		tv.setText(filePath);
		List<Group<BookFile>> list = engine.initFileList(filePath);
		adapter.setData(list);
		for (int i = 0; i < list.size(); i++)
		{
			elv.expandGroup(i);
		}
		if (list.size() == 0) iv_empty.setVisibility(View.VISIBLE);
		else iv_empty.setVisibility(View.GONE);

	}

	@Click(value = { R.id.rl_back })
	public void onClick(View view)
	{

		switch (view.getId())
		{
		case R.id.rl_back:
			if (parentFile == null || parentFile.equals("/")) finish();
			else setFileView(parentFile);
			break;
		}

	}

	@Override
	public void finish()
	{
		super.finish();
		overridePendingTransition(R.anim.push_keep, R.anim.push_right_out);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		setFileView(tv.getText().toString());
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		BookFile file = adapter.getChild(groupPosition, childPosition);
		final File f = new File(file.path);
		if (f.canRead())
		{
			if (f.isDirectory()) setFileView(file.path);
			else
			{
				Book book = new Book(f);
				Book oldBook = db.findByWhere(Book.class, " path= '" + book.path + "'");
				if (oldBook == null)
				{
					db.save(book);
				}
				IntentUtil.startIntent(this, FBReader.class, FBReader.BOOK_KEY, f.getPath());
			}
		}
		else T.ShortToast("对不起，您的访问权限不足!");

		return true;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
	{
		if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD)
		{
			long packedPos = elv.getExpandableListPosition(position);
			int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
			int childPosition = ExpandableListView.getPackedPositionChild(packedPos);
			BookFile file = adapter.getChild(groupPosition, childPosition);

			if (new File(file.path).isDirectory()) ;// TODO
			else IntentUtil.startIntent(this, DeleteBookDialog_.class, "file", file, "MSG", DeleteBookDialog.DELETE_FILE);

			return true;
		}

		return false;
	}

}
