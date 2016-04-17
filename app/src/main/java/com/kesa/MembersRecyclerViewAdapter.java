package com.kesa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kesa.profile.User;
import com.kesa.util.ImageEncoder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * An adapter responsible for displaying the users.
 *
 * @author hongil
 */
public class MembersRecyclerViewAdapter
        extends RecyclerView.Adapter<MembersRecyclerViewAdapter.ViewHolder> {

    private List<User> members;
    private ImageEncoder imageEncoder;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView nameTextView;
        TextView programTextView;
        ImageView profileImageView;

        public ViewHolder(
                View itemView,
                TextView nameTextView,
                TextView programTextView,
                ImageView profileImageView) {
            super(itemView);
            this.nameTextView = nameTextView;
            this.programTextView = programTextView;
            this.profileImageView = profileImageView;
        }
    }

    @Inject
    public MembersRecyclerViewAdapter(ImageEncoder imageEncoder) {
        this.imageEncoder = imageEncoder;
        members = new ArrayList<>();
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MembersRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_members, parent, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView programTextView = (TextView) view.findViewById(R.id.programTextView);
        ImageView profileImageView = (ImageView) view.findViewById(R.id.profileImageView);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(view, nameTextView, programTextView, profileImageView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User currentUser = members.get(position);
        holder.nameTextView.setText(currentUser.getName());
        holder.programTextView.setText(currentUser.getProgram());
        holder.profileImageView.setImageBitmap(imageEncoder.decodeBase64(currentUser.getProfileImage()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return members.size();
    }

    /** Inserts the given {@code user} in the member list. */
    public void insertItem(User user) {
        members.add(members.size(), user);
        notifyItemInserted(members.size()-1);
    }

    /** Clears the data in {@code members} */
    public void clear() {
        members.clear();
        notifyDataSetChanged();
    }
}
