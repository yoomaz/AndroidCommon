package com.graypn.cmmon.permission.permissionmaster.listener;

import com.graypn.cmmon.permission.permissionmaster.model.MultiplePermissionsReport;

/**
 * Empty implementation of {@link MultiplePermissionsListener} to allow extensions to implement
 * only the required methods
 */
public class EmptyMultiplePermissionsListener implements MultiplePermissionsListener {

  @Override
  public void onPermissionsChecked(MultiplePermissionsReport report) {

  }

}
